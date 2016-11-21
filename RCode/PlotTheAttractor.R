library(data.table)
#
dd <- fread("../results.txt", header = F)
plot(dd$V2, dd$V3)
library(plotly)

p <- plot_ly(dd, x = ~V2, y = ~V3, z = 0,
             type = 'scatter3d', mode = 'lines',
             line = list(color = 'red', width = 1))
p



dd$group <- 1
#
dd1 <- fread("../results1.txt", header = F)
names(dd1) <- c("X1", "X2", "X3")
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
