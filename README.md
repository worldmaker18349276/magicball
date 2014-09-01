magicball
=========

library for designing twisty puzzle simulator

# Goal
現在網路上有許多魔術方塊的模擬程式，大部分都是預先定義魔術方塊可以怎麼轉，並沒辦法處理未定義的操作，因此很少見到可以模擬 jumbling 的程式，因為 jumbling 的機制不容易分析，很難把所有的情形都編入程式。但如果以幾何分析的方式檢查操作的可行性，而非預先設定應該怎麼轉，就能處理類似 jumbling 不可預測的操作。
這個計畫最主要的目的就是寫出一個 API 提供給魔術方塊模擬程式設計者，讓他們可以使用這個 API 設計具有模擬 jumbling 能力的魔術方塊模擬器。

# Inspiration
- Ultimate Magic Cube: http://www.ultimatemagiccube.com/
- Interlocked: http://interlocked.wecreatestuff.com/
- Virtual Magic Polyhedra: http://users.skynet.be/moz071262/Applets/Magic%20Polyhedra/
