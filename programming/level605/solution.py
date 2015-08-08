def gen_coins():
    for a in range(2, 5):
        for b in range(a+1, 50):
            for c in range(b+1, 50):
                for d in range(c+1, 50):
                    for e in range(d+1, 50):
                        yield [1, a, b, c, d, e]

least_units = 350
first = -1
for coins in gen_coins():
    units = [0]*100
    for amount in range(1, 100):
        pieces = amount
        for coin in [c for c in coins if c <= amount]:
            pieces = min(1+units[amount-coin], pieces)
        units[amount] = pieces
    t_units = sum(units)
    if 0 < t_units < least_units:
        least_units = t_units
        print(coins, t_units, t_units/99)
