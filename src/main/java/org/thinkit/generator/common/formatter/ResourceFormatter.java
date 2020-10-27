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

package org.thinkit.generator.common.formatter;

import org.thinkit.generator.common.dto.Resource;
import org.thinkit.generator.common.dto.ResourceMatrix;

import lombok.NonNull;

/**
 * リソース定義からリソースを生成する処理を抽象化したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface ResourceFormatter<I extends ResourceMatrix, R extends Resource> {

    /**
     * {@code resourceMatrix} に格納されたリソース情報からリソースを生成し返却します。
     *
     * @param resourceMatrix リソース定義を格納したデータクラス。このデータクラスは必ずマーカーインターフェースの
     *                       {@link ResourceMatrix} を実装しているクラスである必要があります。
     * @return リソース定義から生成されたリソースを格納したデータクラス。このデータクラスは必ずマーカーインターフェースである
     *         {@link Resource} を実装しているクラスである必要があります。
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public R format(@NonNull I resourceMatrix);
}
