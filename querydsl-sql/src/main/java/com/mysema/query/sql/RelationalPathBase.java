package com.mysema.query.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.mysema.query.types.Expression;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BeanPath;

/**
 * @author tiwe
 *
 * @param <T>
 */
public class RelationalPathBase<T> extends BeanPath<T> implements RelationalPath<T> {
    
    private static final long serialVersionUID = -7031357250283629202L;
    
    @Nullable
    private PrimaryKey<T> primaryKey;
    
    @Nullable
    private Expression<?>[] all;
    
    private final List<Expression<?>> columns = new ArrayList<Expression<?>>();
    
    private final List<ForeignKey<?>> foreignKeys = new ArrayList<ForeignKey<?>>();
    
    private final List<ForeignKey<?>> inverseForeignKeys = new ArrayList<ForeignKey<?>>();

    public RelationalPathBase(Class<? extends T> type, String variable) {
        super(type, variable);
    }

    public RelationalPathBase(Class<? extends T> type, PathMetadata<?> metadata) {
        super(type, metadata);
    }

    protected PrimaryKey<T> createPrimaryKey(Path<?>... columns){
        primaryKey = new PrimaryKey<T>(this, columns);
        return primaryKey;
    }
    
    protected <F> ForeignKey<F> createForeignKey(Path<?> local, String foreign){
        ForeignKey<F> foreignKey = new ForeignKey<F>(this, local, foreign);
        foreignKeys.add(foreignKey);
        return foreignKey;
    }
    
    protected <F> ForeignKey<F> createForeignKey(List<? extends Path<?>> local, List<String> foreign){
        ForeignKey<F> foreignKey = new ForeignKey<F>(this, local, foreign);
        foreignKeys.add(foreignKey);
        return foreignKey;
    }
    
    protected <F> ForeignKey<F> createInvForeignKey(Path<?> local, String foreign){
        ForeignKey<F> foreignKey = new ForeignKey<F>(this, local, foreign);
        inverseForeignKeys.add(foreignKey);
        return foreignKey;
    }
    
    protected <F> ForeignKey<F> createInvForeignKey(List<? extends Path<?>> local, List<String> foreign){
        ForeignKey<F> foreignKey = new ForeignKey<F>(this, local, foreign);
        inverseForeignKeys.add(foreignKey);
        return foreignKey;
    }
    
    public Expression<?>[] all() {
        if (all == null || all.length != columns.size()){
            all = new Expression[columns.size()];
            columns.toArray(all);
        }
        return all;
    }
    
    @Override
    protected <P extends Path<?>> P add(P path){
        columns.add(path.asExpr());
        return path;
    }
    
    @Override
    public List<Expression<?>> getColumns() {
        return columns;
    }

    @Override
    public Collection<ForeignKey<?>> getForeignKeys() {
        return foreignKeys;
    }

    @Override
    public Collection<ForeignKey<?>> getInverseForeignKeys() {
        return inverseForeignKeys;
    }

    @Override
    public PrimaryKey<T> getPrimaryKey() {
        return primaryKey;
    }

}