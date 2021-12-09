package ds.bit;

public class BitOperation {

  public int setBit(int num, int idx) {
    // return num | ()
    return 0;
  }

  public int clearBit(int num, int idx) {
    return num & ~(1 << idx);
  }

  public int clearLeftBits(int num, int idx) {
    return num & ((1 << idx) - 1);
  }

  public int clearRightBits(int num, int idx) {
    return num & (-1 << (idx + 1));
  }

  public int updateBit(int num, int idx, boolean val) {
    return num & ~(1 << idx) | ((val ? 1 : 0) << idx);
  }
}
