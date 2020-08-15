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
 * コンテンツの条件ノードを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentConditionNode implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5000914024927667235L;

    /**
     * 条件ID
     */
    @Getter
    private String conditionId;

    /**
     * 除外
     */
    @Getter
    private boolean exclude;

    /**
     * 条件群
     */
    @Getter
    private ContentConditionGroup contentConditionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentConditionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId           条件ID
     * @param exclude               除外
     * @param contentConditionGroup 条件群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentConditionNode(@NonNull String conditionId, boolean exclude,
            @NonNull ContentConditionGroup contentConditionGroup) {
        this.conditionId = conditionId;
        this.exclude = exclude;
        this.contentConditionGroup = contentConditionGroup;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentConditionNode コピーする条件ノードオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentConditionNode(@NonNull ContentConditionNode contentConditionNode) {
        this.conditionId = contentConditionNode.getConditionId();
        this.exclude = contentConditionNode.isExclude();
        this.contentConditionGroup = ContentConditionGroup.of(contentConditionNode.getContentConditionGroup());
    }

    /**
     * 引数として指定された情報をもとに {@link ContentConditionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId           条件ID
     * @param exclude               除外
     * @param ContentConditionGroup 条件群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentConditionNode of(@NonNull String conditionId, boolean exclude,
            @NonNull ContentConditionGroup ContentConditionGroup) {
        return new ContentConditionNode(conditionId, exclude, ContentConditionGroup);
    }

    /**
     * 引数として指定された {@code contentConditionNode} オブジェクトの情報をもとに
     * {@link ContentConditionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param ContentConditionNode 条件ノードオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentConditionNode of(@NonNull ContentConditionNode contentConditionNode) {
        return new ContentConditionNode(contentConditionNode);
    }
}