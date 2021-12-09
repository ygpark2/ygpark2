package ds.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ATM {

  private Map<Integer, Integer> moneyInfoMap = new HashMap<>();

  private List<Integer> nominalMoneyList;

  public ATM() {
    moneyInfoMap.put(1, 20);
    moneyInfoMap.put(5, 8);
    moneyInfoMap.put(10, 11);
    moneyInfoMap.put(50, 10);
    moneyInfoMap.put(100, 5);

    this.nominalMoneyList = moneyInfoMap.keySet().stream().sorted().collect(Collectors.toList());
  }

  public int getMoneyCnt(int money, int moneyNote, int cnt) {
    while(money < moneyNote * cnt) {
      cnt--;
    }
    return cnt;
  }

  public Map<Integer, Integer> widthdraw(int money, int idx, Map<Integer, Integer> moneyMap) {

    if (idx < 0)
      return moneyMap;
    else {
      int nm = this.nominalMoneyList.get(idx);
      int cm = this.moneyInfoMap.get(nm);
      int moneyCnt = getMoneyCnt(money, nm, cm);
      int newMoney = money - nm * moneyCnt;

      moneyMap.put(nm, moneyCnt);
      this.widthdraw(newMoney, idx - 1, moneyMap);
    }
    return moneyMap;
  }

  public Map<Integer, Integer> getMoneyInfoMap() {
    return moneyInfoMap;
  }
}
