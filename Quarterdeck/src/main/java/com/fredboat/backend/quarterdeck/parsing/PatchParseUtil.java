/*
 * MIT License
 *
 * Copyright (c) 2016-2018 The FredBoat Org https://github.com/FredBoat/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fredboat.backend.quarterdeck.parsing;

import java.util.Map;

/**
 * Created by napster on 28.03.18.
 */
public class PatchParseUtil {

    /**
     * @return the long parsed from the provided map of attributes and provided key
     *
     * @throws LongParseException
     *         If we were not able to parse the input into a long.
     */
    public static long parseLong(String key, Map<String, Object> attributes) {
        Object value = attributes.get(key);
        if (value instanceof Long) {
            return (long) value;
        } else if (value instanceof Number) {
            //this can lead to precision loss, but only for values that would be anyways outside of the expected long range
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return Long.parseLong((String) (value));
            } catch (Exception e) {
                throw new LongParseException(key, value);
            }
        } else {
            throw new LongParseException(key, value);
        }
    }

    /**
     * @return the boolean parsed from the provided map of attributes and provided key
     *
     * @throws ParseCastException
     *         If anything went wrong.
     */
    public static boolean parseBoolean(String key, Map<String, Object> attributes) throws ParseCastException {
        return cast(key, attributes, Boolean.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(String key, Map<String, Object> attributes, Class<T> clazz) {
        Object value = attributes.get(key);
        try {
            return (T) value;
        } catch (Exception e) {
            throw new ParseCastException(key, value, clazz, e);
        }
    }
}