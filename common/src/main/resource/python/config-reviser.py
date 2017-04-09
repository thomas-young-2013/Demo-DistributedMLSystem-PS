import sys

if __name__ == "__main__":
    # confirm the env.
    fileLocation, id = sys.argv[1], int(sys.argv[2])
    port_base=8080
    if fileLocation.split("/")[-1] == "master.properties":
        with open(fileLocation, "w") as f:
            ports = range(port_base+1, port_base+id+1)
            nodes = []
            for port in ports:
                nodes.append("localhost:%d" % port)

            f.write("worker=%s\n" % ",".join(nodes))
            f.write("server=localhost:8000\n")
            f.write("port=8999\n")
    else:
        with open(fileLocation, "w") as f:
            f.write("host=worker%d\n" % id)
            f.write("port=%d\n" % (port_base+id))
            f.write("master=localhost:8999\n")
            f.write("metric_gap=100\n")
            f.write("time2sleep=1\n")
