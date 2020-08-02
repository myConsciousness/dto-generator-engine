/**
 * Project Name : generator-commons<br>
 * File Name : ContentConditionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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