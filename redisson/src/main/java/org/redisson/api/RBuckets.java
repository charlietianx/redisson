/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.List;
import java.util.Map;

public interface RBuckets {

    /**
     * <p>Returns a list of object holder instances by a key pattern.
     *
     * <pre>Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *    h[^e]llo matches hallo, hbllo, ... but not hello
     *    h[a-b]llo matches hallo and hbllo</pre>
     * <p>Use \ to escape special characters if you want to match them verbatim.
     * 
     * @param <V> type of value
     * @param pattern - pattern of key
     * @return List of bucket objects
     */
    <V> List<RBucket<V>> find(String pattern);
    
    /**
     * Returns Redis object mapped by key. Result Map is not contains
     * key-value entry for null values.
     * 
     * @param <V> type of value
     * @param keys - keys
     * @return Map with name of bucket as key and bucket as value
     */
    <V> Map<String, V> get(String ... keys);

    /**
     * Try to save objects mapped by Redis key.
     * If at least one of them is already exist then 
     * don't set none of them.
     *
     * @param buckets - map of buckets
     * @return <code>true</code> if object has been set overwise <code>false</code>
     */
    boolean trySet(Map<String, ?> buckets);

    /**
     * Saves objects mapped by Redis key.
     *
     * @param buckets - map of buckets
     */
    void set(Map<String, ?> buckets);
    
}
