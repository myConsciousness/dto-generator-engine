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
 * コンテンツのマトリクス情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentMatrix implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 7166357277027973034L;

    /**
     * コンテンツメタ
     */
    private ContentMeta contentMeta;

    /**
     * コンテンツ作成者
     */
    private ContentCreator contentCreator;

    /**
     * コンテンツバージョン
     */
    private ContentVersion contentVersion;

    /**
     * 選択ノード群
     */
    private ContentSelectionNodeGroup contentSelectionNodeGroup;

    /**
     * 条件ノード群
     */
    private ContentConditionNodeGroup contentConditionNodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param contentMeta               コンテンツメタ
     * @param contentCreator            コンテンツ作成者
     * @param contentVersion            コンテンツバージョン
     * @param contentSelectionNodeGroup 選択ノード群
     * @param contentConditionNodeGroup 条件ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull ContentMeta contentMeta, @NonNull ContentCreator contentCreator,
            @NonNull ContentVersion contentVersion, @NonNull ContentSelectionNodeGroup contentSelectionNodeGroup,
            @NonNull ContentConditionNodeGroup contentConditionNodeGroup) {
        this.contentMeta = contentMeta;
        this.contentCreator = contentCreator;
        this.contentVersion = contentVersion;
        this.contentSelectionNodeGroup = contentSelectionNodeGroup;
        this.contentConditionNodeGroup = contentConditionNodeGroup;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentMatrix コンテンツマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull ContentMatrix contentMatrix) {
        this.contentSelectionNodeGroup = ContentSelectionNodeGroup.of(contentMatrix.getContentSelectionNodeGroup());
        this.contentConditionNodeGroup = ContentConditionNodeGroup.of(contentMatrix.getContentConditionNodeGroup());
    }

    /**
     * 引数として指定された情報を基に {@link ContentMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMeta               コンテンツメタ
     * @param contentCreator            コンテンツ作成者
     * @param contentVersion            コンテンツバージョン
     * @param contentSelectionNodeGroup 選択ノード群
     * @param contentConditionNodeGroup 条件ノード群
     * @return {@link ContentMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMatrix of(@NonNull ContentMeta contentMeta, @NonNull ContentCreator contentCreator,
            @NonNull ContentVersion contentVersion, @NonNull ContentSelectionNodeGroup contentSelectionNodeGroup,
            @NonNull ContentConditionNodeGroup contentConditionNodeGroup) {
        return new ContentMatrix(contentMeta, contentCreator, contentVersion, contentSelectionNodeGroup,
                contentConditionNodeGroup);
    }

    /**
     * 引数として指定された {@code contentMatrix} の情報を基に {@link ContentMatrix}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMatrix コンテンツマトリクス
     * @return {@link ContentMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMatrix of(@NonNull ContentMatrix contentMatrix) {
        return new ContentMatrix(contentMatrix);
    }
}