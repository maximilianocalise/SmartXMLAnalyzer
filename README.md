#Usage

sbt run <input_origin_file_path> <input_other_sample_file_path> <reference_element_id>

#Algorithm 

The Algorithm assigns a score to each element in the html to be evaluated according to its similarity with the reference element.
The score is calculated using the Sorensen-Dice coefficient this way: element score = Sorensen-Dice coefficient(reference element text, target element text) + sum(Sorensen-Dice coefficient(reference element attribute, target element attribute)
Then it chosses the element with the highest score

#Improvements

* Take into account the elements surrounding the reference/target element(i.e. siblings and parent) when calculating the score
* Add unit/integration tests
* Use a better Html parser(the one I have found gave me some problems) 
