# Compound Interest Calculations

## India's Economic Growth Analysis

### Calculation: India's economy from $3.55T to $10T at 8% growth rate

```python
# Let's calculate the number of years required for India's economy to grow from $3.55T to $10T at an 8% growth rate.
# The formula for exponential growth is: 
#     future_value = present_value * (1 + growth_rate) ** years

# Let's solve for "years" using the logarithmic form of the equation:
#     years = log(future_value / present_value) / log(1 + growth_rate)

import math

# Given data
present_value = 3.55  # in trillion dollars
future_value = 10     # in trillion dollars
growth_rate = 0.08    # 8% growth rate

# Calculate the number of years
years = math.log(future_value / present_value) / math.log(1 + growth_rate)

# Determine the target year
current_year = 2024
target_year = current_year + math.ceil(years)

years, target_year
```

## Mathematical Formulas

**log_base_y(y^n) = n**

**CI = P (1 + r/100)^n**

**future_value = present_value * (1 + growth_rate) ^ years**

**(1 + growth_rate) ^ years = future_value / present_value**

**years = ln(future_value/present_value)/ln(1 + growth_rate)**

---

## India vs China Economic Comparison

### Calculation: When will India's economy catch up with China's?

```python
# Let's calculate the number of years required for India's economy to catch up with China's economy at their respective growth rates.

# The formula for each country's growth over time is:
#     future_value_country = present_value_country * (1 + growth_rate) ** years

# We want to find the number of years where:
#     future_value_india = future_value_china

# Thus,
#     3.55 * (1 + 0.08) ** years = 17.79 * (1 + 0.052) ** years

# Solving this equation for "years".

# Re-import necessary libraries since the execution state has reset
import math

# Given data
present_value_india = 3.55  # in trillion dollars (India's current economy)
growth_rate_india = 0.08    # India's growth rate (8%)
present_value_china = 17.79 # in trillion dollars (China's current economy)
growth_rate_china = 0.052   # China's growth rate (5.2%)
current_year = 2024         # Current year

# Calculate the number of years required for India to catch up with China
# Solving (1.08)**years * 3.55 = (1.052)**years * 17.79 for "years".

# Applying the logarithmic transformation to isolate "years"
years = math.log(present_value_china / present_value_india) / math.log((1 + growth_rate_india) / (1 + growth_rate_china))

# Calculate the target year
target_year = current_year + math.ceil(years)

years, target_year
```

## Manual Calculation

**(1.08)^years * 3.55 = (1.052)^years * 17.79**

**(1.08)^years = (1.052)^years * 5.011**

**y * ln(1.08) = y * ln(1.052) + ln(5.011)**

**y * (ln (1.08) - ln(1.052)) = ln(5.011)**

**y = 61.3986**

**Y = 2024 + 61**