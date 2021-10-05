# mobiquity-challenge
Mobiquity Challenge Design or Implementation Considerations
- Resuse existing software where possible. In this case used a commons maths library for generating possible package items combinations.
- Avoid premature optimizations. The implementation is O(2^n), which is not the best should also be fine for values of n up to 15 as specified in the requirement. 
I went for this approach in order to do exhaustive searching of all potential package item combinations. I also avoided any optimizations to keep the solution 
readable.
- Used combonent combosition instead of having the whole solution in a single class - the idea being that I should build a solution on the foundation of well tested
combonents that are individually easy to understand and manatain the source code. 
