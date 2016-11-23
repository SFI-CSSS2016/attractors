library(data.table)
#
dd <- fread("../rossler_results_00.txt", header = F)
#plot(dd[1:1024,]$V2, dd[1:1024,]$V3)
names(dd) <- c("time", "x", "y", "z")
ee <- fread("../../simplevoronoi/voronoi_edges.txt")
names(ee) <- c("x", "y", "xend", "yend", "p1", "p2")
#
library(ggplot2)
p <- ggplot(data = dd[1:1024,], aes(x = x, y = y)) +
  theme_bw() + geom_point(color = "cornflowerblue") + geom_path(color = "red2") +
  geom_segment(data = ee, aes(x = x, y = y, xend = xend, yend = yend))
p


library(plotly)
p <- plot_ly(dd, x = ~x, y = ~y, z = ~z,
             type = 'scatter3d', mode = 'points',
             line = list(color = 'cornflowerblue', width = 1),
             name = "Rössler attractor")
p



dd$group <- 1
#
dd1 <- fread("../results.txt", header = F)
names(dd1) <- c("t", "x", "y", "z")
p <- plot_ly(dd1, x = ~x, y = ~y, z = ~z,
             type = 'scatter3d', mode = 'points',
             line = list(color = 'red', width = 1),
             name = "Rössler attractor")
p



dd1$group2 <- 2
#
dat <- cbind(dd, dd1)
diff <- dd$V2[1:1024] - dd1$X2[1:1024]

library(plotly)

p <- plot_ly(dat, x = ~V2, y = ~V3, z = 0,
             type = 'scatter3d', mode = 'lines',
             line = list(color = 'red', width = 1)) %>%
  add_trace(x = ~X2, y = ~X3, z = ~X4,
            line = list(color = 'blue', width = 1))
p

data <- read.csv('https://raw.githubusercontent.com/plotly/datasets/master/_3d-line-plot.csv')
