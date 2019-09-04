use book::net::tcp::connect;

#[cfg(test)]
#[test]
fn test_socket_client() {
    connect(String::from("https://www.baidu.com"));
}
