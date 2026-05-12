class Solution(object):
    def twoSum(self, numbers, target):
        answer = []
        
        i=0
        j=len(numbers)-1

        while True:
            if numbers[i]+numbers[j]==target:
                answer.append(i+1)
                answer.append(j+1)
                break
            elif numbers[i]+numbers[j] > target:
                j-=1
            else:
                i+=1
                
        return answer 