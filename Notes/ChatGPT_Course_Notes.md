# ChatGPT Course Notes

## Lesson: Prompt Engineering

**Tokens:** ChatGPT understands tokens not words.

**Temperature:** Degree of Randomness in generating next token. If high, next token will be very random/gibberish. If low, it will be definitive.

**Maximum Length:** Total number of tokens that ChatGPT will generate.

**Top P:** Another metric to control randomness. This controls smallest subset of tokens whose sum of probabilities >= the specified input metric. Expressed as a number between 0 - 1.

**Frequency Penalty:** Controls the frequencies of words returned. If high, less/no words will be repeated.

**Presence Penalty:** Factor which makes it stick to subject in question. If high, noise will be reduced.

## Best Tips for Good Prompting

* Make ChatGPT pretend to be an expert
  Eg "Consider that you are a doctor"

* Define your objective as specifically as possible. Provide as much context as possible.
* Ask your answers in a certain format eg - Table, Bullet Points, Paragraphs.
* Prompt by example when it becomes difficult to explain the format. Provide an example in those circumstances.
* When not getting good responses, ask ChatGPT to ask you questions to improve your question in the first place.
* Refine your answers and counter question.