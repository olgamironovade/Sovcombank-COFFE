import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from seaborn import regression
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeRegressor


def recomend():
    n = 6
    usd_rub_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/RUB=X?period1=1652918400&period2=1668816000&interval=1d&events=history&includeAdjustedClose=true")
    usd_rub_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/RUB=X?period1=1652918400&period2=1668816000&interval=1wk&events=history&includeAdjustedClose=true")
    usd_rub_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/RUB=X?period1=1652918400&period2=1668816000&interval=1mo&events=history&includeAdjustedClose=true")

    euro_rub_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURRUB=X?period1=1652918400&period2=1668816000&interval=1d&events=history&includeAdjustedClose=true")
    euro_rub_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURRUB=X?period1=1652918400&period2=1668816000&interval=1wk&events=history&includeAdjustedClose=true")
    euro_rub_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURRUB=X?period1=1652918400&period2=1668816000&interval=1mo&events=history&includeAdjustedClose=true")

    euro_usd_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURUSD=X?period1=1637289209&period2=1668825209&interval=1d&events=history&includeAdjustedClose=true")
    euro_usd_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURUSD=X?period1=1637289209&period2=1668825209&interval=1wk&events=history&includeAdjustedClose=true")
    euro_usd_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/EURUSD=X?period1=1637289209&period2=1668825209&interval=1mo&events=history&includeAdjustedClose=true")

    funt_rub_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPRUB=X?period1=1652918400&period2=1668816000&interval=1d&events=history&includeAdjustedClose=true")
    funt_rub_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPRUB=X?period1=1652918400&period2=1668816000&interval=1wk&events=history&includeAdjustedClose=true")
    funt_rub_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPRUB=X?period1=1652918400&period2=1668816000&interval=1mo&events=history&includeAdjustedClose=true")

    funt_usd_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPUSD=X?period1=1652918400&period2=1668816000&interval=1d&events=history&includeAdjustedClose=true")
    funt_usd_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPUSD=X?period1=1652918400&period2=1668816000&interval=1wk&events=history&includeAdjustedClose=true")
    funt_usd_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPUSD=X?period1=1652918400&period2=1668816000&interval=1mo&events=history&includeAdjustedClose=true")

    funt_euro_day = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPEUR=X?period1=1652918400&period2=1668816000&interval=1d&events=history&includeAdjustedClose=true")
    funt_euro_week = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPEUR=X?period1=1652918400&period2=1668816000&interval=1wk&events=history&includeAdjustedClose=true")
    funt_euro_month = pd.read_csv(
        "https://query1.finance.yahoo.com/v7/finance/download/GBPEUR=X?period1=1652918400&period2=1668816000&interval=1mo&events=history&includeAdjustedClose=true")

    money = [usd_rub_day, usd_rub_week, usd_rub_month, euro_rub_day, euro_rub_week, euro_rub_month, euro_usd_day,
         euro_usd_week, euro_usd_month, funt_rub_day, funt_rub_week, funt_rub_month,funt_usd_day,funt_usd_week,funt_usd_month,funt_euro_day,funt_euro_week,funt_euro_month]
    most_v = []
    name_money = ['usd->rub', 'euro->rub', 'euro->usd', 'funt->rub', 'funt->usd','funt->euro']

    for i in range(3 * n):
        data = money[i]

        plt.figure(figsize=(10, 4))
        plt.title("RUB - USD Exchange Rate")
        plt.xlabel("Date")
        plt.ylabel("Close")
        plt.plot(data["Close"])
        plt.show()

        x = data[["Open", "High", "Low"]]
        y = data["Close"]
        x = x.to_numpy()
        y = y.to_numpy()
        y = y.reshape(-1, 1)

        xtrain, xtest, ytrain, ytest = train_test_split(x, y, test_size=0.2, random_state=42)

        model = DecisionTreeRegressor()
        model.fit(xtrain, ytrain)
        ypred = model.predict(xtest)

        data = pd.DataFrame(data={"Predicted Rate": ypred.flatten()})
        if i % 3 == 0:
            most_v.append(data['Predicted Rate'][6] / data['Predicted Rate'][0])
        elif i % 3 == 1:
            most_v.append(data['Predicted Rate'][5] / data['Predicted Rate'][0])
        else:
            most_v.append(data['Predicted Rate'][1] / data['Predicted Rate'][0])

    return 'Самая выгодный перевод на следующую неделю: ', name_money[np.array(most_v[::3]).argmax()], 'Самая выгодный перевод на следующий месяц: ', name_money[np.array(most_v[1::3]).argmax()],'Самая выгодный перевод на следующий квартал: ', name_money[np.array(most_v[2::3]).argmax()]

print(recomend())