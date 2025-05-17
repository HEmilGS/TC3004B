from fastapi import FastAPI
from pydantic import BaseModel
import firebase_admin
from firebase_admin import credentials, firestore

app = FastAPI()

cred = credentials.Certificate("firebase_credentials.json")  
firebase_admin.initialize_app(cred)
db = firestore.client()

class Resultado(BaseModel):
    a: float
    b: float
    c: float
    d: float
    resultado: float

@app.post("/guardar")
def guardar_resultado(data: Resultado):
    doc_ref = db.collection("resultados").document()
    doc_ref.set(data.dict())
    return {"message": "Resultado almacenado correctamente"}