import sys

if __name__ == "__main__":
    # confirm the env.
    fileLocation, id = sys.argv[1], int(sys.argv[2])
    port_base=8080
    with open(fileLocation, "w") as f:
        f.write("host=worker%d\n" % id)
        f.write("port=%d\n" % (port_base+id))
        f.write("master=localhost:8999\n")
        f.write("time2sleep=1\n")
