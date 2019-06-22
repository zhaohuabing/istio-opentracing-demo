# Use opentracing with Istio for tracing propagation and method-level tracing inside a service

### How to run this demo?
1. deploy istio in a Kubernets cluster, enable sidecar auto injection for default namespace
2. deploy eshop demo
```
git clone https://github.com/zhaohuabing/istio-opentracing-demo.git
kubectl apply -f istio-opentracing-demo/k8s/eshop.yaml
```
3. Open this url in the browser to trigger the eshop service http://${NODE_IP}:31380/checkout
4. Open jeager UI in the browser http://${NODE_IP}:30088/checkout
Note: In order to access jaeger UI, you need to modify the default jaeger service type to NodePort in the Istio installation scripts like below:
```
 apiVersion: v1
  kind: Service
  metadata:
    name: jaeger-query
    namespace: istio-system
    annotations:
    labels:
      app: jaeger
      jaeger-infra: jaeger-service
      chart: tracing
      heritage: Tiller
      release: istio
  spec:
    ports:
      - name: query-http
        port: 16686
        protocol: TCP
        targetPort: 16686
        nodePort: 30088
    type: NodePort
    selector:
      app: jaeger
```

### Istio distributed tracing by explicitly passing the b3 tracing header
* Source code: branch without-opentracing
![](http://)

### Use opentracing and jaeger instrumentation for tracing propagation
* Source code: branch master
![](http://)

### Add method-level spans to Istio/Envoy generated trace
* Source code: branch master
![](http://)

### Understanding what happend
* The eshop demo use opentracing and jaeger for distributed tracing instrumentation. All the REST calls are automatically traced by opentracing.
* A 'Traced' AOP annotation is used for method-level tracing instrumentation.
* Jaeger opentracing implementation handles the trace context propagation crossing process boundaries. So we don't have to explicitly copy the tracing headers from downstream requests to upstream requests.
* Because Istio/Envoy uses b3 header for trace contex propagation, so we need to specify the b3 header format in the environment virables when create the jaeger tracer. The length of trace id must also be set to 128 bit in the environment virables because jaeger tracer uses 64 bit by default.



