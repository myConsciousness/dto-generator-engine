/**
 * Project Name : Generator<br>
 * File Name : DtoClassResource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/15<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOクラスのリソース情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoClassResource {

    /**
     * パッケージ名
     */
    private String packageName = "";

    /**
     * リソースマップ
     */
    private Map<String, String> resources = new HashMap<>(0);

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private DtoClassResource() {
    }

    /**
     * コンストラクタ
     *
     * @param packageName パッケージ名
     * @param resources   リソースマップ
     */
    public DtoClassResource(@NonNull String packageName, @NonNull Map<String, String> resources) {
        this.packageName = packageName;
        this.resources = resources;
    }
}