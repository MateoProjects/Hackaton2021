# Install dependencies
#!pip install numpy
#!pip install torch
#!pip install transformers


################################ Imports ################################
import torch
from transformers import BertForSequenceClassification, BertTokenizer
import numpy as np


################################ Settings ################################
MODEL_TO_USE = r"C:\\Users\\mateo\\Documents\\GitHub\\Hackaton2021\\API\\NLP\\BETO"
DEVICE = "cuda:0" if torch.cuda.is_available() else "cpu" # Use Nvidia GPU if is available


################################ Create model ################################
model = BertForSequenceClassification.from_pretrained(MODEL_TO_USE, num_labels=5)
print(f"Model size = {sum([np.prod(p.size()) for p in model.parameters()])}")
model = model.to(DEVICE)


################################ Create tokenizer ################################
tokenizer = BertTokenizer.from_pretrained(MODEL_TO_USE, do_lower_case=False)
print(f"Number of tokens = {len(tokenizer)}")


################################ Prediction function ################################
def _predict(sentence, model, tokenizer):
  input = tokenizer(sentence, add_special_tokens=True, padding="longest", return_tensors="pt")
  input = input.to(DEVICE)
  output = model(**input)
  output = output.logits.detach().cpu().numpy()
  prediction = np.argmax(output, axis=1)[0]

  return prediction

def predict(sentence):
  return _predict(sentence, model, tokenizer)

################################ Test ################################
#sentence = "Estoy genial!"
#prediction = predict(sentence, model, tokenizer)
#print(f"Prediction = {prediction}")
#prediction = predict("Estoy fatal", model, tokenizer)
#print(f"Prediction = {predict}")
