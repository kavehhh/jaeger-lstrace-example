from ddtrace import tracer
from ddtrace.propagation.b3 import B3HTTPPropagator

tracer.configure(
    http_propagator=B3HTTPPropagator,
    hostname="localhost",
    port=8181
)
tracer.set_tags(
    {
        "lightstep.service_name": "lightstep-py"
    }
)

from flask import Flask
from flask import request

app = Flask(__name__)

@app.route('/')
def index():
    print(request.headers)
    span = tracer.current_span()
    return 'Hello world'

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
