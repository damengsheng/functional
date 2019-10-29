/**
 * module-info
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 2019/10/26 14:44.
 */
module java.standard.net {

    requires java.net.http;
    requires java.security.sasl;
    requires java.security.jgss;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    requires java.standard.tools;

    uses standard.tools.NetUtils;
    uses standard.tools.TimeUtils;



}