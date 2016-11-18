library(data.table)
dd <- fread("../results.txt", header = F)

library(plotly)
p <- plot_ly(dd, x = ~V2, y = ~V3, z = ~V4, type = 'scatter3d', mode = 'points',
             opacity = 1, line = list(width = 0.6, color = "blue"))
p

# Create a shareable link to your chart
# Set up API credentials: https://plot.ly/r/getting-started
chart_link = plotly_POST(p, filename="line3d/basic")
chart_link
