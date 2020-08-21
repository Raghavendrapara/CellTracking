# Cell Tracking with Active Segmentation plugin for ImageJ

This project is sponsored by Google Inc. as a part of the Google Summer of Code 2020 program: 

1. **Organization:** [International Neuroinformatics Coordinating Facility(INCF)](http://incf.org)
2. **Mentor:** Dimiter Prodanov, [INCF Belgian Node](http://www.neuroinformatics.be), Sumit Vohra, ZIB
3. **Student Developer:** [Raghavendra Singh Chauhan](https://github.com/Raghavendra)

## Project description
A Track Linking package based on Viterbi Dynamic Programming Algorithm.

In this(2020) edition of Google summer of code I have worked on extending the Active-Segmentation Plug-In to perform Cell Tracking on Segmented Stack. The technique employed is based on the Viterbi Algorithm widely used in Digital Communications and Natural Language Processing. I would also like to acknowledge the research paper, from where I derive the foundational idea for my project, titled [Global Linking Of Cell Tracks Using The Viterbi Algorithm](https://web.stanford.edu/group/blau/pdfs/Magnusson_Blau_2015.pdf)by Klas E.G Magnusson, Joakim Jalden, Penney M. Gilbert &  Helen M. Blau. 
We start with a image stack and develop a Trellis of states i.e. Detections/Extractions that allows modelling of the images and cells in the form of a Graph.
Phase 1 incorporates code for extracting ROIs from Segmented Images based on a experimental database and moves on to define Class definitons and behaviour for solving the Tracking Problem.Starting with defining a Trellis of states for the Image Stack.
 
Phase 2 I implemented backend for Viterbi Algorithm implementation and worked on GUI part in Active Segmentation plugin.[View my commits to ActiveSeg fork](https://github.com/Raghavendrapara/ACTIVESEGMENTATION/commits/CellTrack)