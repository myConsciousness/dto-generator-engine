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

package org.thinkit.generator.common.vo.content;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの選択ノードを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * ContentSelectionNode contentSelectionNode = ContentSelectionNode.of(conditionId)
 *                                            .put(key1, value1)
 *                                            .put(key2, value2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentSelectionNode implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 3246450914037753704L;

    /**
     * 条件ID
     */
    @Getter
    private String conditionId;

    /**
     * コンテンツ選択グループ
     */
    @Getter
    private ContentSelectionGroup contentSelectionGroup;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ContentSelectionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId           条件ID
     * @param contentSelectionGroup コンテンツ選択グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelectionNode(@NonNull String conditionId, @NonNull ContentSelectionGroup contentSelectionGroup) {
        this.conditionId = conditionId;
        this.contentSelectionGroup = contentSelectionGroup;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelectionNode 選択ノード
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionNode(@NonNull ContentSelectionNode contentSelectionNode) {
        this.conditionId = contentSelectionNode.getConditionId();
        this.contentSelectionGroup = ContentSelectionGroup.of(contentSelectionNode.getContentSelectionGroup());
    }

    /**
     * {@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId           条件ID
     * @param contentSelectionGroup コンテンツ選択グループ
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull String conditionId,
            @NonNull ContentSelectionGroup contentSelectionGroup) {
        return new ContentSelectionNode(conditionId, contentSelectionGroup);
    }

    /**
     * 引数として渡された {@code contentSelectionNode}
     * オブジェクトの情報をコピーした{@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentSelectionNode 選択ノード
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull ContentSelectionNode contentSelectionNode) {
        return new ContentSelectionNode(contentSelectionNode);
    }
}