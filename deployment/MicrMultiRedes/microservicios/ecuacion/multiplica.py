from fastapi import FastAPI
from pydantic import BaseModel
import requests

app = FastAPI()

class Input(BaseModel):
    a: float
    b: float
    c: float
    d: float

@app.post("/resolver")
def resolver(valores: Input):
    suma_resp = requests.post("http://suma:8000/sumar", json={"a": valores.a, "b": valores.b})
    resta_resp = requests.post("http://resta:8000/restar", json={"c": valores.c, "d": valores.d})

    suma = suma_resp.json()["resultado"]
    resta = resta_resp.json()["resultado"]

    resultado = suma * resta

    # Llamar al microservicio de almacenamiento
    requests.post("http://almacenamiento_firebase:8000/guardar", json={
        "a": valores.a,
        "b": valores.b,
        "c": valores.c,
        "d": valores.d,
        "resultado": resultado
    })

    return {"resultado": resultado}