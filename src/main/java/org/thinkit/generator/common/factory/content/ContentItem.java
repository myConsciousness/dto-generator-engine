/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.generator.common.factory.json.Item;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの項目を生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see #createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentItem extends Item {

    /**
     * コンストラクタ
     *
     * @param key   キー
     * @param value 値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentItem(@NonNull String key, @NonNull String value) {
        super(key, value);
    }

    @Override
    public String createResource() {
        return String.format("\"%s\" : \"%s\"", super.getKey(), super.getValue());
    }
}