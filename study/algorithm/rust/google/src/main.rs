#![deny(clippy::all)]
#![allow(dead_code)]
#![allow(clippy::collapsible_if)]
#![allow(clippy::needless_range_loop)]
#![allow(clippy::too_many_arguments)]
#![allow(clippy::suspicious_operation_groupings)]

use std::fmt::Write;
use std::io::*;

pub trait RustGymRead: BufRead {
    fn parse_line<T>(&mut self) -> T
    where
        Self: Sized,
        T: std::str::FromStr,
        <T as std::str::FromStr>::Err: std::fmt::Debug,
    {
        let line = self.lines().next().unwrap().unwrap();
        line.trim().parse::<T>().unwrap()
    }

    fn parse_vec<T>(&mut self) -> Vec<T>
    where
        Self: Sized,
        T: std::str::FromStr,
        <T as std::str::FromStr>::Err: std::fmt::Debug,
    {
        let line = self.lines().next().unwrap().unwrap();
        line.split_whitespace()
            .map(|s| s.parse::<T>().unwrap())
            .collect()
    }

    fn parse_mat<T>(&mut self, n: usize) -> Vec<Vec<T>>
    where
        Self: Sized,
        T: std::str::FromStr,
        <T as std::str::FromStr>::Err: std::fmt::Debug,
    {
        let mut res: Vec<Vec<T>> = vec![];
        let mut it = self.lines();
        for _ in 0..n {
            let line = it.next().unwrap().unwrap();
            let row = line
                .split_whitespace()
                .map(|s| s.parse::<T>().unwrap())
                .collect();
            res.push(row);
        }
        res
    }

    fn collect_string(&mut self) -> String
    where
        Self: Sized,
    {
        self.lines().next().unwrap().unwrap()
    }

    fn collect_vec(&mut self) -> Vec<String>
    where
        Self: Sized,
    {
        let line = self.lines().next().unwrap().unwrap();
        line.split_whitespace().map(|s| s.to_string()).collect()
    }

    fn collect_mat(&mut self, n: usize) -> Vec<Vec<String>>
    where
        Self: Sized,
    {
        let mut res: Vec<Vec<String>> = vec![];
        let mut it = self.lines();
        for _ in 0..n {
            let line = it.next().unwrap().unwrap();
            let row = line.split_whitespace().map(|s| s.to_string()).collect();
            res.push(row);
        }
        res
    }
}

impl<R: BufRead> RustGymRead for R {}
fn main() {
    let mut res = "".to_string();
    let mut reader = BufReader::new(stdin());
    let t: usize = reader.parse_line();
    for i in 1..=t {
        solve(i, &mut reader, &mut res);
    }
    print!("{}", res);
}

fn solve(case_no: usize, reader: &mut impl BufRead, writer: &mut impl Write) {
    let line = reader.collect_string();
    writeln!(writer, "Case #{}: {}", case_no, line).unwrap();
}

mod codejam;
mod kickstart;
