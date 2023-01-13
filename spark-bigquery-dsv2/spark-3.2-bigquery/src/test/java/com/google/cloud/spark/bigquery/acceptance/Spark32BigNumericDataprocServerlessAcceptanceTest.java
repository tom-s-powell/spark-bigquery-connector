/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.spark.bigquery.acceptance;

public class Spark32BigNumericDataprocServerlessAcceptanceTest
    extends BigNumericDataprocServerlessAcceptanceTestBase {

  public Spark32BigNumericDataprocServerlessAcceptanceTest() {
    super("spark-3.2-bigquery", "1.0");
  }

  // tests from superclass

}
