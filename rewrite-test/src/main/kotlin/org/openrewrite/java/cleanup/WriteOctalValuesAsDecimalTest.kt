/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.cleanup

import org.junit.jupiter.api.Test
import org.openrewrite.Recipe
import org.openrewrite.java.JavaParser
import org.openrewrite.java.JavaRecipeTest

@Suppress("OctalInteger")
interface WriteOctalValuesAsDecimalTest : JavaRecipeTest {
    override val recipe: Recipe
        get() = WriteOctalValuesAsDecimal()

    @Test
    fun writeAsDecimal(jp: JavaParser) = assertChanged(
        jp,
        before = """
            class Test {
                void test() {
                    int m = 010;
                    short m2 = 010;
                    int n = 0x01;
                    int o = 0b01;
                    int p = 12;
                    int q = 1;
                }
            }
        """,
        after = """
            class Test {
                void test() {
                    int m = 8;
                    short m2 = 8;
                    int n = 0x01;
                    int o = 0b01;
                    int p = 12;
                    int q = 1;
                }
            }
        """
    )
}