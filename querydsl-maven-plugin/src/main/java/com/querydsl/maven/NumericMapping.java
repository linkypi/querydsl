/*
 * Copyright 2013, Mysema Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.querydsl.maven;

import com.querydsl.sql.Configuration;

/**
 * {@linkplain NumericMapping} is used to customize mappings of various numeric precisions to data types.
 *
 * @author tiwe
 *
 */
public class NumericMapping implements Mapping {

    public int total, decimal;

    public String javaType;

    @Override
    public void apply(Configuration configuration) {
        try {
            configuration.registerNumeric(total, decimal, Class.forName(javaType));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
