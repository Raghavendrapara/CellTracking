# Cell Tracking with Active Segmentation plugin for ImageJ

This project is sponsored by Google Inc. as a part of the Google Summer of Code 2020 program: 

1. **Organization:** [International Neuroinformatics Coordinating Facility(INCF)](http://incf.org)
2. **Mentor:** Dimiter Prodanov, [INCF Belgian Node](http://www.neuroinformatics.be), Sumit Vohra, ZIB
3. **Student Developer:** [Raghavendra Singh Chauhan](https://github.com/Raghavendra)

## Project description
A Track Linking package based on Viterbi Dynamic Programming Algorithm.


**This repository contains some methods that work in backend for the algorithm implementation and further work was done in tandem with the Active Segmentation repository for frontend GUI portion**

In this(2020) edition of Google summer of code I have worked on extending the Active-Segmentation Plug-In to perform Cell Tracking on Segmented Stack. The technique employed is based on the Viterbi Algorithm widely used in Digital Communications and Natural Language Processing. I would also like to acknowledge the research paper, from where I derive the foundational idea for my project, titled [Global Linking Of Cell Tracks Using The Viterbi Algorithm](https://web.stanford.edu/group/blau/pdfs/Magnusson_Blau_2015.pdf)by Klas E.G Magnusson, Joakim Jalden, Penney M. Gilbert &  Helen M. Blau. 

We start with a image stack and develop a Trellis of states i.e. Detections/Extractions that allows modelling of the images and cells in the form of a Graph.

For more info on Cell Tracking using Viterbi Algorithm one can refer to my PPT [here](https://docs.google.com/presentation/d/1Rx_-JiC9foyHs3ZAdX_37jRgOOl6WfSTX3nAg0Fi2tg/edit?usp=sharing)

Phase 1 We worked on incorporating code for extracting ROIs from Segmented Images based on a experimental database and moves on to define Class definitons and behaviour for solving the Tracking Problem.Starting with defining a Trellis of states for the Image Stack.

**Training Panel**

A still from Fixed Event Model based Training
![ScreenShot](https://github.com/Raghavendrapara/CellTracking/blob/GitHubPage/Images/Training.png) 
 
 
Phase 2 We implemented backend for Viterbi Algorithm implementation and worked on GUI part in Active Segmentation plugin.[View my commits to ActiveSeg fork](https://github.com/Raghavendrapara/ACTIVESEGMENTATION/commits/CellTrack)


Trellis Of States of Cell Detections(Ref-Klas Magnusson Cell Tracking Paper)
![ScreenShot](https://github.com/Raghavendrapara/CellTracking/blob/GitHubPage/Images/1.png)


Random Forest Graph that we desire as output(Ref-Klas Magnusson Paper)
![ScreenShot](https://github.com/Raghavendrapara/CellTracking/blob/GitHubPage/Images/2.png)

Phase 3 Our basic frontend and backend was complete and we worked on a sample use case implementation to test the viability for future modifications.


**References**
<ul>
 <li>Global Linking Of Cell Tracks Using Viterbi Algorithm-Klas Magnusson</li>
 <li>Efficient Algorithms for Moral Lineage Tracing-Florian Jug</li>
 <li>Min Cost Flow Network based Object Tracking</li>
</ul>

**Scope for Improvements**
<ul>
<li>Improve on GUI for displaying Tracks in a elegant manner</li>
<li>Incorporate ML Classifiers for better tracking results</li>
<li>Implement Swap functionality for optimizing run time</li>
</ul>


**Future developments** 
<ul>
<li>Extending current tracking module with deep learning techniques</li>
<li>Expanding user interaction and implementing better User Interface </li>
<li>Optimizing efficiency and increasing accuracy to deal with faulty segmentation </li>
</ul>
