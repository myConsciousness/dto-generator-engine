/**
 * Project Name : generator-commons<br>
 * File Name : ResourceFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import lombok.NonNull;

/**
 * プログラムリソースの各構成要素を抽象化したオブジェクトを返却するファクトリクラスです。 {@link ResourceFactory}
 * を継承した具象クラスは必ず各抽象メソッドを実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class ResourceFactory {

    /**
     * {@link Item} クラスの新しいインスタンスを生成し返却します。
     *
     * @param key   キー
     * @param value 値
     * @return {@link Item} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract Item createItem(@NonNull String key, @NonNull String value);

    /**
     * {@link ItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ItemGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract ItemGroup createItemGroup();

    /**
     * {@link Node} クラスの新しいインスタンスを生成し返却します。
     *
     * @param itemGroup 項目グループ
     * @param nodeGroup ノードグループ
     * @return {@link Node} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract Node createNode(@NonNull ItemGroup itemGroup, @NonNull NodeGroup nodeGroup);

    /**
     * {@link NodeGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @param key キー
     * @return {@link NodeGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract NodeGroup createNodeGroup(@NonNull String key);

    /**
     * {@link LeafVertex} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link LeafVertex} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract LeafVertex createLeafVertex();

    /**
     * {@link Resource} クラスの新しいインスタンスを生成し返却します。
     *
     * @param leafVertex 葉頂点
     * @return {@link Resource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract Resource createResource(@NonNull LeafVertex leafVertex);
}