export interface OptionVote {
  voteOption: String;
  voteCount: number
}
export interface Poll {
  question: String;
  options: OptionVote[]
}
