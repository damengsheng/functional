use book::asyncs::greeting;
use book::net::tcp;

fn main() {
    tcp::connect(String::from("www.baidu.com"));

    let future = greeting::hello();
    futures::executor::block_on(future);
}
