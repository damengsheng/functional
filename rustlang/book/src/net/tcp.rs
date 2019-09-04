use std::io::prelude::*;
use std::io::{Read, Result, Write};
use std::net::{SocketAddr, TcpStream};

pub fn connect(url: String) -> Result<()> {
    let mut socket = TcpStream::connect(url)?;
    socket.write(&[1])?;
    socket.read(&mut [0, 128])?;
    Ok(())
}
