import pandas as pd
df = pd.read_csv("bmw.csv")

def model_order(model):
    if("Series" in model):
        texto = model.split()
        return texto[1]+" "+texto[0]
    return model

df['model'] = df['model'].apply(model_order)
print(df)
