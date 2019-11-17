'''
Created on Out 16, 2019

@author: ulissesnairnedealmeida
'''
import pandas as pd;
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn import metrics
from sklearn.preprocessing import Normalizer
import numpy as np


dados_monitorameto = pd.read_excel('/Users/ulissesnairnedealmeida/OneDrive/Documentos/Estudos/Pós-Graduação/Machine Learning/Projeto Aplicado/banco/BASE_MONITORAMENTO.xlsx')

# Dados Carregados
print(dados_monitorameto)

# Realizando a limpeza dos dados

#Removendo a coluna não utilizada
dados_monitorameto = dados_monitorameto.drop(['MON_UMIDADE'], axis=1)

# Convertendo os dados da coluna para datetime 
dados_monitorameto["MON_DATA_HORA"] = pd.to_datetime(dados_monitorameto["MON_DATA_HORA"])

# Criando colunas novas para o tatamento do dataset
dados_monitorameto["DIA"] = dados_monitorameto['MON_DATA_HORA'].map(lambda x: x.day)
dados_monitorameto["HORA"] = dados_monitorameto['MON_DATA_HORA'].map(lambda x: x.hour)

# Em momentos que o consumo é menor que 0.5 podemos econizar (pois pode se tratar de um standby de TV ou uma lâmpada ligada)
dados_monitorameto["PODE_ECNOMIZAR"] = dados_monitorameto['MON_COSUMO_ENERGIA'].map(lambda x: x < 0.5)

# Imprimindo dias e horários que podemos economizar energia
print(dados_monitorameto[dados_monitorameto["PODE_ECNOMIZAR"]==True])
print("Em [%.f] momentos podemos economizar energia"%len(dados_monitorameto[dados_monitorameto["PODE_ECNOMIZAR"]==True]))


# deletando colunas não utilizadas
dados_normalizados = dados_monitorameto.drop(['MON_DATA_HORA', 'MON_COD', 'MON_LUG_COD', 'MON_DIS_COD', 'MON_LIGADO', 'PODE_ECNOMIZAR'], axis=1)
dados_monitorameto = dados_monitorameto.drop(['MON_DATA_HORA', 'MON_COD', 'MON_LUG_COD', 'MON_DIS_COD', 'MON_LIGADO'], axis=1)


#Normalizando a escala
dados_normalizados.iloc[:,:] = Normalizer(norm='l1').fit_transform(dados_normalizados)

# Seprando informações para treino e teste
X_train, X_test, y_train, y_test = train_test_split(dados_normalizados.values, dados_monitorameto.values, test_size=0.3, random_state=42)

#Ajustando os indices para os valores máximos do eixo indices of the maximum values along an axis
y_train = np.argmax(y_train, axis=1)

# Criando o Modelo de Nayves Bayes
modelo = GaussianNB()
modelo = modelo.fit(X_train, y_train)
predict = modelo.predict(X_train)

# Calculando a acurácia do Modelo
acuracia = metrics.accuracy_score(y_train, predict)

previsto = modelo.predict(X_test)
y_test = np.argmax(y_test, axis=1)
print(metrics.classification_report(y_test, previsto))

verdadeiro_negativo, falso_positivo, falso_negativo, verdadeiro_positivo = metrics.confusion_matrix(y_test, previsto).ravel()

print("Para os testes realizados com [%.f] registros dos quais [%.f] foram classificados como verdadeiro negativo,\n[%.f] foram classificados verdadeiro positivo, [%.f] foram classificados como falso positivo e [%.f] foram classificados como falso negativo\ngerando uma acurácia de [%.2f] para o modelo." % (len(y_test),verdadeiro_negativo, verdadeiro_positivo, falso_positivo, falso_negativo, acuracia) )
