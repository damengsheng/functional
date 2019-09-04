extern crate postgres;

use postgres::params::{ConnectParams, Host};
use postgres::{Connection, TlsMode};

struct Person {
    id: i32,
    name: String,
    data: Option<Vec<u8>>,
}

fn main() {
    let params = ConnectParams::builder()
        .user("root", Option::Some("qweasd"))
        .build(Host::Tcp("localhost".to_string()));
    let conn = Connection::connect(
        params, /*"postgres://postgres@localhost:5432"*/
        TlsMode::None,
    )
    .unwrap();
    conn.execute(
        "CREATE TABLE person (
                   id      SERIAL PRIMARY KEY,
                   name    VARCHAR NOT NULL,
                   data    BYTEA
                   )",
        &[],
    )
    .unwrap();
    let me = Person {
        id: 0,
        name: "Steven".to_string(),
        data: None,
    };
    conn.execute(
        "INSERT INTO person (name, data) VALUES ($1, $2)",
        &[&me.name, &me.data],
    )
    .unwrap();
    for row in &conn
        .query("SELECT id, name, data FROM person", &[])
        .unwrap()
    {
        let person = Person {
            id: row.get(0),
            name: row.get(1),
            data: row.get(2),
        };
        println!("Found person {}: {}", person.id, person.name);
    }
}
