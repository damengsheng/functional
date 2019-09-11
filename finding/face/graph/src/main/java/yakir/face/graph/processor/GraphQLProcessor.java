package yakir.face.graph.processor;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.idl.SchemaParser;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.FieldInfo;
import io.github.classgraph.FieldInfoList;
import io.github.classgraph.ScanResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yakir.face.graph.anno.Struct;

import java.util.Set;


/**
 * GraphQLProcessor
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 16:12.
 */
public class GraphQLProcessor {

    private static final Logger log = LogManager.getLogger(GraphQLProcessor.class);

    private SchemaParser schemaParser;

    public GraphQLProcessor() {
        schemaParser = new SchemaParser();
    }

    public Set<Class<?>> scanStruct() {

        String pkg             = "";
        String routeAnnotation = Struct.class.getName();

        String schema = "";

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().disableModuleScanning().whitelistPackages(pkg).scan()) {
            ClassInfoList classInfoList = scanResult.getClassesWithAnnotation(routeAnnotation);
            for (ClassInfo classInfo : classInfoList) {

                log.info("{} {} {}", classInfo.getName(), classInfo.getDeclaredFieldInfo(), classInfo.getDeclaredMethodAndConstructorInfo());
                GraphQLObjectType.Builder graphqlObjectTypeBuilder = GraphQLObjectType.newObject()
                        .name(classInfo.getSimpleName())
                        .description(classInfo.getName());

                FieldInfoList fieldList = classInfo.getDeclaredFieldInfo();
                for (FieldInfo fieldInfo : fieldList) {
                    graphqlObjectTypeBuilder
                            .field(GraphQLFieldDefinition.newFieldDefinition()
                                    .name(fieldInfo.getName())
                                    .type(GraphQLTypeReference.typeRef("String")));
                }

                graphqlObjectTypeBuilder.build();
            }
        }

        return null;
    }

}
