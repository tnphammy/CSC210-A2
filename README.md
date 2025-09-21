# A2 Genetic Algorithms

Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Members: N/A

Any peers and/or TAs/Tutors you collaborated with: N/A

Would you like to give "kudos" to anyone who was particularly supportive or helpful? N/A

Cite any references used: https://www.w3schools.com/java/java_ref_arraylist.asp, https://stackoverflow.com/questions/16644811/converting-a-sublist-of-an-arraylist-to-an-arraylist, https://stackoverflow.com/questions/63342254/how-can-i-compare-two-chars

If you used AI, please describe how you used it and what the experience taught you: N/A


## Questions

Please briefly describe what you observed about the "winners" produced by your genetic algorithm. Did changing the parameter values have any effect on what you observed?

- A higher number of rounds produced more palindromic chromosomes. Although, from my observation, the best results plateaud at around 70 rounds (this will depend on the other factors, but I believe there will be a plateau in every case.)
- Lower chances of mutation of each gene produced better results.

## Reflection

Please provide a reflection on your experience with this assignment-- what was interesting? what was hard? what do you feel like you learned? 
- Thoughts: This assignment was actually really fun compared to the last 2! It felt logical and straightforward. The idea of multiple generations that evolved reminded me a lot of machine learning and it is very interesting to see that idea come to life.
- What was hard: I did get quite confused by the idea of making offspring in generations, as I was left wondering whether new offspring would belong to a new generation (I believe they should), however, they were added to the same previous population (`pop`) ArrayList. Also the indexing bugs in the loops are just hard to catch because we randomly generate numbers a lot, and so sometimes it would get a bug, a lot of times it wouldn't. 
- What I learned: to be very careful when it comes to indeces, I should slow down and really read the instructions, especially if I'm using something new like Random which can take a min and max bound. 