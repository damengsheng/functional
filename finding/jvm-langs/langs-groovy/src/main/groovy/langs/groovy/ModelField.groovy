package langs.groovy

import groovy.transform.builder.Builder

/**
 * Model
 *
 * @author yakir on 2019/11/18 22:07.
 */
@Builder
class ModelField {

    String name;
    Model model;
}
