export interface OptionVote {
  voteOption: String;
  voteCount: number
}
export interface Poll {
  id: number;
  question: String;
  options: OptionVote[]
}
