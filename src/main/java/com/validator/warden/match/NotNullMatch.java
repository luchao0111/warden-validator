/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.validator.warden.match;


import com.validator.warden.annotation.Matcher;
import com.validator.warden.match.factory.Builder;

/**
 * 拦截null数据，对应{@link Matcher#notNull()}
 *
 * @author DandyLuo
 */
public class NotNullMatch extends AbstractBlackWhiteMatch implements Builder<NotNullMatch, String> {

    private Boolean notNull;

    @Override
    public NotNullMatch build(final String obj) {
        if (!"".equals(obj)) {
            this.notNull = Boolean.parseBoolean(obj);
        }
        return this;
    }

    @Override
    public boolean match(final Object object, final String name, final Object value) {
        if (this.notNull) {
            if(null != value) {
                this.setBlackMsg("属性 {0} 的值为null", name);
                return true;
            } else{
                this.setWhiteMsg("属性 {0} 的值为null", name);
                return false;
            }
        } else {
            if(null == value) {
                this.setBlackMsg("属性 {0} 的值不为null", name);
                return true;
            } else{
                this.setWhiteMsg("属性 {0} 的值非空", name);
                return false;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (null == this.notNull);
    }
}
