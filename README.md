# jaeger-lstrace-example

To run:

1) cd opentracing-spring-boot-jaeger-example/

docker run \
--rm \
--name jaeger \
-p5775:5775/udp \
-p6831:6831/udp \
-p6832:6832/udp \
-p5778:5778/tcp \
-e JAEGER_PROPAGATION=b3 \
jaegertracing/jaeger-agent:1.16 \
--jaeger.tags=access_token=<access-token> \
--reporter.grpc.host-port=ingest.lightstep.com:443 \
--reporter.grpc.tls=true
  
2) ls-trace-run python server.py
