use rustgym_util::*;
use std::fmt::Write;
use std::io::*;

fn solve(case_no: usize, reader: &mut impl BufRead, writer: &mut impl Write) {
    let args: Vec<usize> = reader.parse_vec();
    let n = args[0];
    let b = args[1] as i32;
    let mut houses: Vec<i32> = reader.parse_vec();
    houses.sort_unstable();
    let mut sum = 0;
    let mut res = 0;
    for i in 0..n {
        if houses[i] + sum <= b {
            sum += houses[i];
            res += 1;
        }
    }
    writeln!(writer, "Case #{}: {}", case_no, res).unwrap();
}

google_test_gen!(test, "input.txt", "output.txt");
