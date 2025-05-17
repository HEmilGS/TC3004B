FROM python:3.10-slim

WORKDIR /microservicios
COPY almacenamiento.py .
COPY firebase_credentials.json .

RUN pip install fastapi uvicorn pydantic firebase-admin

CMD ["uvicorn", "almacenamiento:app", "--host", "0.0.0.0", "--port", "8000"]