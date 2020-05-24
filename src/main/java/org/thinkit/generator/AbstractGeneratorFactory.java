/**
 * Project Name : Generator<br>
 * File Name : AbstractGeneratorFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.catalog.GeneratorDivision;

/**
 * 各業務に対応した生成器を生成する抽象ファクトリクラスです。<br>
 * <br>
 * 使用する際には必要な生成器と紐づく生成器区分を引数として、<br>
 * {@link #create(GeneratorDivision)}を呼び出してください。<br>
 * 各業務に必要な生成器が返却されます。<br>
 * <br>
 * 当抽象クラスを継承する全ての具象サブクラスは必ず{@link #createGenerator(GeneratorDivision)}を実装する必要があります。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractGeneratorFactory {

    /**
     * 引数として指定された生成器区分から各業務処理に対応する生成器を取得し返却します。<br>
     * 当ファクトリ処理が正常に終了するためには当抽象クラスを継承したそれぞれの具象ファクトリクラスが<br>
     * {@link #createGenerator(GeneratorDivision, String)}を各業務に対応した生成器を返却するように正しく実装している必要があります。<br>
     * <br>
     * 起動する生成器区分に加えて第2引数として生成器が操作する対象のファイルへのパスを指定する必要があります。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     * 引数チェックに関しては当メソッドにて実施するため、<br>
     * {@link #createGenerator(GeneratorDivision, String)}の実装時に引数の検査処理を実装する必要はありません。<br>
     * <br>
     * 業務に応じた生成器を取得する際にはサブクラスで実装された{@link #createGenerator(GeneratorDivision, String)}ではなく、<br>
     * {@link #create(GeneratorDivision, String)}を呼び出してください。
     * 
     * @param generatorDivision 生成器区分
     * @param filePath          ファイルパス
     * @return 生成器
     * @see {@link #createGenerator(GeneratorDivision, String)}
     * 
     * @exception NullPointerException     生成器区分がnullの場合
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public final Generator create(final GeneratorDivision generatorDivision, final String filePath) {
        Objects.requireNonNull(generatorDivision, "Generator division must not be null.");

        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException(String.format("File path is required. %s was given.", filePath));
        }

        return this.createGenerator(generatorDivision, filePath);
    }

    /**
     * 引数として指定された生成器区分に基づいて、<br>
     * 各業務に対応した生成器を取得し返却する処理を定義する抽象メソッドです。<br>
     * <br>
     * この抽象メソッドは当抽象クラスの{@link #create(GeneratorDivision)}で使用されるため、<br>
     * 当抽象クラスを継承した具象ファクトリクラスは必ずこの抽象メソッドを実装する必要があります。 <br>
     * <br>
     * この抽象メソッドを実装する際には以下のことを遵守してください。<br>
     * <br>
     * 1, 引数として指定された生成器区分に応じたファクトリクラスを呼び出すこと。<br>
     * 2, 返却する生成器は必ず生成器区分に応じたクラスであること。<br>
     * 
     * @param generatorDivision 生成器区分
     * @param filePath          ファイルパス
     * @return 生成器
     * @see {@link #create(GeneratorDivision, String)}
     * 
     * @exception NullPointerException     生成器区分がnullの場合
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    protected abstract Generator createGenerator(GeneratorDivision generatorDivision, String filePath);
}