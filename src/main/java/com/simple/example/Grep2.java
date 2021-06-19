/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.simple.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

/**
 * A dataflow pipeline that prints the lines that match a specific search term
 *
 * Run:
 *      ./run_Grep2.sh $PROJECT_ID $TEMP_GCS_BUCKET Grep2
 *
 */

public class Grep2 {
    @SuppressWarnings("serial")
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        Pipeline p = Pipeline.create(options);

        String input = "gs://sample-pipelines-bucket/javafiles/*.java";
        String outputPrefix = "gs://sample-pipelines-bucket/output";
        final String searchTerm = "import";

        p //
                .apply("GetJava", TextIO.read().from(input)) //
                .apply("Grep", ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) throws Exception {
                        String line = c.element();
                        if (line.contains(searchTerm)) {
                            c.output(line);
                        }
                    }
                })) //
                .apply(TextIO.write().to(outputPrefix).withSuffix(".txt").withoutSharding());

        p.run();
    }

}

