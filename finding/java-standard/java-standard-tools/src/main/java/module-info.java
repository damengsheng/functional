/**
 * module-info
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 2019/10/26 15:22.
 */
module java.standard.tools {

    requires static org.apache.logging.log4j;

    exports standard.tools;

    opens standard.tools;
}